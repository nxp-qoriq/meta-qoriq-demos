SUMMARY = "Firmwares and Standalone Applications"
LICENSE = "Freescale-Binary-EULA"
LIC_FILES_CHKSUM = "file://NXP-EULA.txt;md5=d969f2c93b3905d4b628787ce5f8df4b"

inherit deploy

SRC_URI = "git://bitbucket.sw.nxp.com/scm/dnnpi/lx2-phy.git;branch=lx2-devel;protocol=https"
SRCREV = "6319609d6fac241239b465762b1540a27a58ec71"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}/boot
    cp -fr ${S}/in112525 ${D}/boot
}

do_deploy () {
    install -d ${DEPLOYDIR}/lx2-phy
    cp -fr ${S}/in112525 ${DEPLOYDIR}/lx2-phy
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_MACHINE = "(lx2160a)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
