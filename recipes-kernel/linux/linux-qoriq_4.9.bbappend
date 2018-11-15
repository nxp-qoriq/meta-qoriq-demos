SRCREV = "fc34f48a55637c87a93f9f03ba4bab47b41ff296"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
