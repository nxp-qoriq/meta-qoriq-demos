SRC_URI = "git://bitbucket.sw.nxp.com/scm/gitam/dpdk.git;branch=17.11-qoriq;protocol=https \
           file://add-RTE_KERNELDIR_OUT-to-split-kernel-bu.patch \
"
SRCREV = "0c0332ae0016ca6742961f1f4ac6e2f4fdbe215a"

do_install_append_lx2160a () {
    rm -rf ${D}${datadir}/dpdk
    rm -rf ${D}${datadir}/buildtools
    rm -rf ${D}${datadir}/examples
    rm -rf ${D}${datadir}/mk
}

COMPATIBLE_MACHINE = "(lx2160a)"
