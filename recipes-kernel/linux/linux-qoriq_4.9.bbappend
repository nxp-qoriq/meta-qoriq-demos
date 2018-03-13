SRC_URI = "git://bitbucket.sw.nxp.com/scm/dnnpi/lx2-linux.git;branch=lx2-devel;protocol=https"
SRCREV = "157fd5c541003a5a3dc35ab06c4f5e3d900a5035"

DELTA_KERNEL_DEFCONFIG_append_lx2160a-simu = " lx2simu.config"
