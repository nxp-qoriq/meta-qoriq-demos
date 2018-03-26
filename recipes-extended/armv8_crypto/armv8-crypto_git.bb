SUMMARY = "ARMv8 Crypto Library"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://README;start_line=4;end_line=30;md5=4c3a3e811c55a3f8c81a660a46a92321"

SRC_URI = "git://github.com/caviumnetworks/armv8_crypto.git;branch=master;protocol=https"
SRCREV = "d498a6ff4694104b70117b14812e4b5a78bc320e"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" CFLAGS="-O3 -Wall -static -I${S} -I${S}/asm/include"'

do_install () {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -m 755 ${S}/libarmv8_crypto.a ${D}${libdir}
    install -m 644 ${S}/asm/include/armv8_crypto_defs.h ${D}${includedir}
}

COMPATIBLE_MACHINE = "(ls1012a)"
