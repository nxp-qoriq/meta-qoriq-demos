FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

require recipes-devtools/qemu/qemu.inc

inherit ptest

RDEPENDS_${PN}-ptest = "bash make"

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

PV = "2.9.0+fsl"

SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/qemu;nobranch=1 \
           file://powerpc_rom.bin \
           file://run-ptest \
           "

SRCREV = "798304eeb99ec1d2f8910275a3505f964a73c651"

S = "${WORKDIR}/git"

COMPATIBLE_HOST_mipsarchn32 = "null"
COMPATIBLE_HOST_mipsarchn64 = "null"

EXTRA_OECONF  = "--prefix=${prefix} --target-list=aarch64-softmmu --enable-fdt --enable-kvm --with-system-pixman --disable-werror"

DISABLE_STATIC = ""

do_install_append() {
    # Prevent QA warnings about installed ${localstatedir}/run
    if [ -d ${D}/usr${localstatedir}/run ]; then rm -fr ${D}/usr${localstatedir}; fi
    install -Dm 0755 ${WORKDIR}/powerpc_rom.bin ${D}${datadir}/qemu
}

do_compile_ptest() {
	make buildtest-TESTS
}

do_install_ptest() {
	cp -rL ${B}/tests ${D}${PTEST_PATH}
	find ${D}${PTEST_PATH}/tests -type f -name "*.[Sshcod]" | xargs -i rm -rf {}

	cp ${S}/tests/Makefile.include ${D}${PTEST_PATH}/tests
	# Don't check the file genreated by configure
	sed -i -e '/wildcard config-host.mak/d' \
	       -e '$ {/endif/d}' ${D}${PTEST_PATH}/tests/Makefile.include
}

INSANE_SKIP_${PN} += "already-stripped"

# FIXME: Avoid WARNING due missing patch for native/nativesdk
BBCLASSEXTEND = ""
