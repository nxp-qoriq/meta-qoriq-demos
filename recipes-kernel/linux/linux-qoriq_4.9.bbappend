SRCREV = "4548b3d5cf3a0d9d7177e44005bfbb05f9530d2a"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
