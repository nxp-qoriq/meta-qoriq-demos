SRC_URI = "git://bitbucket.sw.nxp.com/scm/dnnpi/lx2-linux.git;branch=lx2-devel;protocol=https"
SRCREV = "93b53b177e675e7e2a5ac69a363937fa58251c9b"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
