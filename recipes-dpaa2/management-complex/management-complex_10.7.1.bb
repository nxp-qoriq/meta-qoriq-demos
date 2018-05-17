SUMMARY = "DPAA2 Management Complex Firmware"
LICENSE = "NXP-Binary-EULA"
LIC_FILES_CHKSUM = "file://NXP-Binary-EULA.txt;md5=afcb1213054384820390d410ab62105f"

inherit deploy

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "git://bitbucket.sw.nxp.com/scm/dpaa2/mc-binary.git;branch=lx2160a;protocol=https"
SRCREV = "736a485535fa8593aff66b2f2584d4b32eb68838"

S = "${WORKDIR}/git"

REGLEX_ls2088a = "ls2088a"
REGLEX_ls1088a = "ls1088a"
REGLEX_lx2160a = "lx2160a"

do_install () {
    install -d ${D}/boot
    install -m 755 ${S}/${REGLEX}/*.itb ${D}/boot
}

do_deploy () {
    install -d ${DEPLOYDIR}/mc_app
    install -m 755 ${S}/${REGLEX}/*.itb ${DEPLOYDIR}/mc_app
    # make a symlink to the latest binary
    for mc_binary in `ls ${DEPLOYDIR}/mc_app |sort`;do
        ln -sfT ${mc_binary} ${DEPLOYDIR}/mc_app/mc.itb
    done
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

INHIBIT_PACKAGE_STRIP = "1"

COMPATIBLE_MACHINE = "(ls2080ardb|ls2088a|ls1088a|lx2160a)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
