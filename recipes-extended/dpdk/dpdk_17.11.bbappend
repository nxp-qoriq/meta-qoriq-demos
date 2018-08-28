SRC_URI = "git://bitbucket.sw.nxp.com/scm/gitam/dpdk.git;branch=17.11-qoriq;protocol=https \
           file://add-RTE_KERNELDIR_OUT-to-split-kernel-bu.patch \
"
SRCREV = "dbb2592747bac13a3a4825f19c57603956c0219b"

do_install_append_lx2160a () {
    rm -rf ${D}${datadir}/dpdk
    rm -rf ${D}${datadir}/buildtools
    rm -rf ${D}${datadir}/examples
    rm -rf ${D}${datadir}/mk
}

COMPATIBLE_MACHINE = "(lx2160a)"
