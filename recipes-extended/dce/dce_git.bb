DESCRIPTION = "Decompression Compression Engine Userspace Utils"
SECTION = "utils"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=338308e2a663929309c9929ab9495bb5"

SRC_URI = " \
  git://bitbucket.sw.nxp.com/scm/dpaa2/dce.git;branch=master;protocol=https \
  git://bitbucket.sw.nxp.com/scm/dpaa2/qbman_userspace.git;branch=qman50;protocol=https;name=qbman;destsuffix=git/lib/qbman_userspace \
  file://0001-Fix-QA-issue.patch \
"
SRCREV = "7d2a2f8529891d4ba15a83ddb3e44d759fc7a165"
SRCREV_qbman = "ecf3647e894aa259556d3dcf35a55725230b8cc5"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" AR="${AR}"'

do_compile_prepend () {
    unset CFLAGS
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
