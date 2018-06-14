SRC_URI = "git://gitolite3@git.codeaurora.org:22/external/private_lx2160a/lx2-linux;nobranch=1;protocol=ssh"
SRCREV = "e9b545829d7f35189709c8b29a07ffbe2adaa2f0"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
