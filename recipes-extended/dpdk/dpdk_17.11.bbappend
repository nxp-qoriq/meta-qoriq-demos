SRC_URI = "git://bitbucket.sw.nxp.com/scm/gitam/dpdk.git;branch=17.11-qoriq;protocol=https \
           file://add-RTE_KERNELDIR_OUT-to-split-kernel-bu.patch \
"
SRCREV = "af17971becea88228a5edda2cb44805891ff54e0"

do_install_append_lx2160a () {
    rm -rf ${D}${datadir}/dpdk
    rm -rf ${D}${datadir}/buildtools
    rm -rf ${D}${datadir}/examples
    rm -rf ${D}${datadir}/mk
}

COMPATIBLE_MACHINE = "(lx2160a)"
