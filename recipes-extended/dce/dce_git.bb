CRIPTION = "Decompression Compression Engine Userspace Utils"
SECTION = "utils"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3bb80dec5c1b94d99a1422cbfd96192c"

SRC_URI = "git://bitbucket.sw.nxp.com/scm/~nxa07713/dce.git;branch=jira/QSDK-5067;protocol=https \
           git://bitbucket.sw.nxp.com/scm/dpaa2/qbman_userspace.git;name=qbman;destsuffix=git/lib/qbman_userspace \
           file://0001-Fix-QA-issue.patch \
"
SRCREV = "e8c2dae7bf31ca3a60d96d0ceb628ad224dcc2bc"
SRCREV_qbman = "75ff61a7ca6acdbdbb780161b053cbcbc990f1be"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" AR="${AR}"'

do_compile_prepend () {
    unset CFLAGS
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
