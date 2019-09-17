FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "git://bitbucket.sw.nxp.com/dash/optee_os.git;protocol=ssh;nobranch=1 \
           file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
          "
SRCREV = "e93f053213c574ec1b97f9e56b2f31692cd3723c"
OPTEEMACHINE_ls1046afrwy = "ls1046ardb"

PACKAGE_ARCH = "${MACHINE_ARCH}"
