DESCRIPTION = "Decompression Compression Engine Userspace Utils"
SECTION = "utils"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=338308e2a663929309c9929ab9495bb5"

SRC_URI = " \
  git://source.codeaurora.org/external/qoriq/qoriq-components/dce;nobranch=1 \
  git://source.codeaurora.org/external/qoriq/qoriq-components/qbman_userspace;nobranch=1;name=qbman;destsuffix=git/lib/qbman_userspace \
  file://0001-Fix-QA-issue.patch \
"
SRCREV = "9b927b33d908b169a6aac58067a3752a6744fb47"
SRCREV_qbman = "ecf3647e894aa259556d3dcf35a55725230b8cc5"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" AR="${AR}"'

do_compile_prepend () {
    unset CFLAGS
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
