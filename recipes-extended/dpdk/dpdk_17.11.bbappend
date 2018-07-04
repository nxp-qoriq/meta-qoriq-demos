SRC_URI = "git://bitbucket.sw.nxp.com/scm/gitam/dpdk.git;branch=17.11-lx2-qoriq;protocol=https \
           file://add-RTE_KERNELDIR_OUT-to-split-kernel-bu.patch \
"
SRCREV = "90afdb61c1ba40834fff2ef002fb42cfe405dfff"

do_install_append_lx2160a () {
    rm -rf ${D}${datadir}/dpdk
    rm -rf ${D}${datadir}/buildtools
    rm -rf ${D}${datadir}/examples
    rm -rf ${D}${datadir}/mk
}

COMPATIBLE_MACHINE = "(lx2160a)"
