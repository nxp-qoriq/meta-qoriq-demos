FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "git://bitbucket.sw.nxp.com/dash/optee_os.git;protocol=ssh;nobranch=1 \
           file://0001-allow-setting-sysroot-for-libgcc-lookup.patch \
          "
SRCREV = "4e8d2e5307b99a91a0cac3ea3560ecb7d62898d6"
OPTEEMACHINE_ls1046afrwy = "ls1046ardb"

PACKAGE_ARCH = "${MACHINE_ARCH}"
