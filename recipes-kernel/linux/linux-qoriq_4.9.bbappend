SRCREV = "c0795d038e8a0de27a6937ae4aa889c4138c1409"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
