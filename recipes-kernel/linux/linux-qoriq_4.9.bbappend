SRCREV = "0350ec79cdbdabd0583938c6154eeffd84f768a7"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
