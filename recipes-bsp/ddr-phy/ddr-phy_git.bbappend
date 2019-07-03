SRC_URI = "git://github.com/nxp/ddr-phy-binary.git;fsl-eula=true;nobranch=1 \
    git://source.codeaurora.org/external/qoriq/qoriq-components/atf;nobranch=1;nobranch=1;destsuffix=git/atf;name=atf"
SRCREV = "14d03e6e748ed5ebb9440f264bb374f1280b061c"
SRCREV_atf = "17f94e4315e81e3d1b22d863d9614d724e8273dc"

do_install () {
    oe_runmake -C ${S}/atf fiptool
    cd ${S}/${REGLEX}
    ${S}/atf/tools/fiptool/fiptool create --ddr-immem-udimm-1d ddr4_pmu_train_imem.bin \
    --ddr-immem-udimm-2d ddr4_2d_pmu_train_imem.bin \
    --ddr-dmmem-udimm-1d ddr4_pmu_train_dmem.bin \
    --ddr-dmmem-udimm-2d ddr4_2d_pmu_train_dmem.bin \
    --ddr-immem-rdimm-1d ddr4_rdimm_pmu_train_imem.bin \
    --ddr-immem-rdimm-2d ddr4_rdimm2d_pmu_train_imem.bin \
    --ddr-dmmem-rdimm-1d ddr4_rdimm_pmu_train_dmem.bin \
    --ddr-dmmem-rdimm-2d ddr4_rdimm2d_pmu_train_dmem.bin \
    fip_ddr_all.bin
    install -d ${D}/boot
    install -m 755 ${S}/${REGLEX}/*.bin ${D}/boot
}

do_deploy () {
    install -d ${DEPLOYDIR}/ddr-phy
    install -m 755 ${S}/${REGLEX}/*.bin ${DEPLOYDIR}/ddr-phy
}
