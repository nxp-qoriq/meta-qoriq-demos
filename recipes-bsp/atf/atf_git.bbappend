SRC_URI = "git://bitbucket.sw.nxp.com/gitam/atf.git;protocol=ssh;nobranch=1"
SRCREV = "8fc3b19e40c5ccd61c2f43f34cae47d8b1165926"
UEFI_QSPIBOOT_ls1046ardb ?= "LS1046ARDB_EFI_QSPIBOOT.fd"

DEPENDS_append_qoriq-arm64 += "${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'optee-os-qoriq', '', d)}"
DEPENDS_append_lx2160a += "ddr-phy"
chassistype_ls1046afrwy = "ls104x_1012"

BOOTTYPES ?= "nor qspi flexspi_nor"
do_compile() {
    export LIBPATH="${RECIPE_SYSROOT_NATIVE}"
    install -d ${S}/include/tools_share/openssl
    cp -r ${RECIPE_SYSROOT}/usr/include/openssl/*   ${S}/include/tools_share/openssl
    ${RECIPE_SYSROOT_NATIVE}/usr/bin/cst/gen_keys 1024

    if [ "${BUILD_FUSE}" = "true" ]; then
       ${RECIPE_SYSROOT_NATIVE}/usr/bin/cst/gen_fusescr ${RECIPE_SYSROOT_NATIVE}/usr/bin/cst/input_files/gen_fusescr/${chassistype}/input_fuse_file
       fuseopt="fip_fuse FUSE_PROG=1 FUSE_PROV_FILE=fuse_scr.bin"
    fi
    if [ "${BUILD_SECURE}" = "true" ]; then
        secureopt="TRUSTED_BOARD_BOOT=1 ${ddrphyopt} CST_DIR=${RECIPE_SYSROOT_NATIVE}/usr/bin/cst"
        secext="_sec"
        bl33="${uboot_boot_sec}"
        if [ ${chassistype} = ls104x_1012 ]; then
            rcwtemp="${rcwsec}"
        else
            rcwtemp="${rcw}"
        fi
    else
        bl33="${uboot_boot}"
        rcwtemp="${rcw}"
    fi       

    if [ "${BUILD_OPTEE}" = "true" ]; then
        bl32="${DEPLOY_DIR_IMAGE}/optee/tee_${MACHINE}.bin" 
        bl32opt="BL32=${bl32}"
        spdopt="SPD=opteed" 
    fi
    if [ -f ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_pmu_train_dmem.bin ]; then
        cp ${DEPLOY_DIR_IMAGE}/ddr-phy/*.bin ${S}/
    fi

    for d in ${BOOTTYPES}; do
        case $d in
        nor)
            rcwimg="${RCWNOR}${rcwtemp}.bin"
            uefiboot="${UEFI_NORBOOT}"
            ;;
        nand)
            rcwimg="${RCWNAND}${rcwtemp}.bin"
            ;;
        qspi)
            rcwimg="${RCWQSPI}${rcwtemp}.bin"
            uefiboot="${UEFI_QSPIBOOT}"
            if [ "${BUILD_SECURE}" = "true" ] && [ ${MACHINE} = ls1046ardb ]; then
                rcwimg="RR_FFSSPPPH_1133_5559/rcw_1600_qspiboot_sben.bin"
            fi
            ;;
        sd)
            rcwimg="${RCWSD}${rcwtemp}.bin"
            ;;
        flexspi_nor)
            rcwimg="${RCWXSPI}${rcwtemp}.bin"
            uefiboot="${UEFI_XSPIBOOT}"
            ;;        
        esac
            
	if [ -f "${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${rcwimg}" ]; then
                oe_runmake V=1 -C ${S} realclean
                oe_runmake V=1 -C ${S} all fip pbl PLAT=${PLATFORM} BOOT_MODE=${d} POLICY_OTA=1 RCW=${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${rcwimg} BL33=${bl33} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt}
                cp -r ${S}/build/${PLATFORM}/release/bl2_${d}*.pbl ${S}
                cp -r ${S}/build/${PLATFORM}/release/fip.bin ${S}
                if [ "${BUILD_FUSE}" = "true" ]; then
                    cp -f ${S}/build/${PLATFORM}/release/fuse_fip.bin ${S}
                fi

                if [ -n "${uefiboot}" ]; then
                    oe_runmake V=1 -C ${S} realclean
                    oe_runmake V=1 -C ${S} all fip pbl PLAT=${PLATFORM} BOOT_MODE=${d} POLICY_OTA=1 RCW=${DEPLOY_DIR_IMAGE}/rcw/${PLATFORM}/${rcwimg} BL33=${DEPLOY_DIR_IMAGE}/uefi/${PLATFORM}/${uefiboot} ${bl32opt} ${spdopt} ${secureopt} ${fuseopt}
                    cp -r ${S}/build/${PLATFORM}/release/fip.bin ${S}/fip_uefi.bin
                fi
        fi
        rcwimg=""
        uefiboot=""
    done
}

do_install() {
    install -d ${D}/boot/atf
    cp -r ${S}/srk.pri ${D}/boot/atf
    cp -r ${S}/srk.pub ${D}/boot/atf
    if [ "${BUILD_SECURE}" = "true" ]; then
        secext="_sec"
    fi
    if [ -f "${S}/fip_uefi.bin" ]; then
        cp -r ${S}/fip_uefi.bin ${D}/boot/atf/fip_uefi.bin
    fi
    if [ -f "${S}/fuse_fip.bin" ]; then
        cp -r ${S}/fuse_fip.bin ${D}/boot/atf/fuse_fip.bin
    fi
    if [ -f "${S}/fip.bin" ]; then
        cp -r ${S}/fip.bin ${D}/boot/atf/fip.bin
    fi
    for d in ${BOOTTYPE}; do
        if [ -e  ${S}/bl2_${d}${secext}.pbl ]; then
            cp -r ${S}/bl2_${d}${secext}.pbl ${D}/boot/atf/bl2_${d}${secext}.pbl
        fi
    done
    if [ -f "${S}/fip_ddr_sec.bin" ]; then
        cp -r ${S}/fip_ddr_sec.bin ${D}/boot/atf/fip_ddr_sec.bin
    fi
    chown -R root:root ${D}
}
do_deploy() {
    install -d ${DEPLOYDIR}/atf
    cp -r ${D}/boot/atf/srk.pri ${DEPLOYDIR}/atf
    cp -r  ${D}/boot/atf/srk.pub ${DEPLOYDIR}/atf
    if [ "${BUILD_SECURE}" = "true" ]; then
        secext="_sec"
    fi

    if [ -f "${S}/fuse_fip.bin" ]; then
        cp -r ${D}/boot/atf/fuse_fip.bin ${DEPLOYDIR}/atf/fuse_fip${secext}.bin
    fi

    if [ -e ${D}/boot/atf/fip_uefi.bin ]; then
        cp -r ${D}/boot/atf/fip_uefi.bin ${DEPLOYDIR}/atf/fip_uefi.bin
    fi
    cp -r ${D}/boot/atf/fip.bin ${DEPLOYDIR}/atf/fip_uboot${secext}.bin
    for d in ${BOOTTYPE}; do
        if [ -e ${D}/boot/atf/bl2_${d}${secext}.pbl ]; then
            cp -r ${D}/boot/atf/bl2_${d}${secext}.pbl ${DEPLOYDIR}/atf/bl2_${d}${secext}.pbl
        fi
    done

    if [ -f "${S}/fip_ddr_sec.bin" ]; then
        cp -r ${D}/boot/atf/fip_ddr_sec.bin ${DEPLOYDIR}/atf/fip_ddr_sec.bin
    fi
}
