DESCRIPTION = "ARM Trusted Firmware"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=e927e02bca647e14efd87e9e914b2443"

inherit deploy

DEPENDS = "ddr-phy rcw uefi"

S = "${WORKDIR}/git"

SRC_URI = "git://bitbucket.sw.nxp.com/scm/gitam/atf.git;branch=dev01;protocol=https \
           file://0001-plat-nxp-fix-tool-build-issue.patch \
"
SRCREV = "6e88b1d9561d776f7767f972b4c6799c835c53a7"

BOOTTYPE ?= "flexspi_nor" 
UEFI_BIN ?= "${DEPLOY_DIR_IMAGE}/uefi/${MACHINE}/LX2160ARDB_EFI.fd" 
RCW_BIN ?= "${DEPLOY_DIR_IMAGE}/rcw/${MACHINE}/XGGFF_PP_HHHH_19_5_2/rcw_2000_700_2400_19_5_2.bin"
do_configure[noexec] = "1"
do_install[noexec] = "1"
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_compile() {
      cd ${S}
      export CROSS_COMPILE="${TARGET_PREFIX}"
      oe_runmake V=1 PLAT=${MACHINE} BL33=${UEFI_BIN} pbl BOOT_MODE=${BOOTTYPE} RCW=${RCW_BIN} fip
      tools/fiptool/fiptool create \
        --ddr-immem-udimm-1d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_pmu_train_imem.bin \
        --ddr-immem-udimm-2d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_2d_pmu_train_imem.bin \
        --ddr-dmmem-udimm-1d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_pmu_train_dmem.bin \
        --ddr-dmmem-udimm-2d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_2d_pmu_train_dmem.bin \
        --ddr-immem-rdimm-1d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_rdimm_pmu_train_imem.bin \
        --ddr-immem-rdimm-2d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_rdimm2d_pmu_train_imem.bin \
        --ddr-dmmem-rdimm-1d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_rdimm_pmu_train_dmem.bin \
        --ddr-dmmem-rdimm-2d ${DEPLOY_DIR_IMAGE}/ddr-phy/ddr4_rdimm2d_pmu_train_dmem.bin fip_ddr_all.bin
}

do_deploy() {
      install -d ${DEPLOYDIR}/atf
      cp -f ${S}/fip_ddr_all.bin ${DEPLOYDIR}/atf/
      cp -f ${S}/build/${MACHINE}/release/fip.bin ${DEPLOYDIR}/atf/
      cp -f ${S}/build/${MACHINE}/release/bl2_${BOOTTYPE}.pbl ${DEPLOYDIR}/atf/
}
addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "(lx2160ardb)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
