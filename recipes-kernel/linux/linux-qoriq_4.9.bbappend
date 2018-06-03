SRC_URI = "git://gitolite3@git.codeaurora.org:22/external/private_lx2160a/lx2-linux;nobranch=1;protocol=ssh"
SRCREV = "3f4e0392e8ee409187176b54d7d992cbb20ba7a5"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"

do_deploy_append_lx2160a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
