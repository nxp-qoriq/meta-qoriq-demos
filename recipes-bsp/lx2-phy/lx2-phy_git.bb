SUMMARY = "Firmwares and Standalone Applications"
LICENSE = "Freescale-Binary-EULA"
LIC_FILES_CHKSUM = "file://NXP-EULA.txt;md5=d969f2c93b3905d4b628787ce5f8df4b"

inherit deploy

SRC_URI = "git://github.com/nxp/qoriq-firmware-cortina.git;protocol=https;nobranch=1"
SRCREV = "91eb51dc43606e0d5ce3382c8422407041c8df9a"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}/boot
    cp -fr ${S}/in112525 ${D}/boot
    cp -fr ${S}/aquantia ${D}/boot

    if [ "${MACHINE}" = "lx2160ardb" ];then
        rm -rf ${D}/boot/aquantia/AQ28*.cld
    elif [ "${MACHINE}" = "lx2160aqds" ];then
        rm -rf ${D}/boot/aquantia/AQR*.cld
    fi
}

do_deploy () {
    install -d ${DEPLOYDIR}/lx2-phy
    cp -fr ${S}/in112525 ${DEPLOYDIR}/lx2-phy
    cp -fr ${S}/aquantia ${DEPLOYDIR}/lx2-phy

    if [ "${MACHINE}" = "lx2160ardb" ];then
        rm -rf ${DEPLOYDIR}/lx2-phy/aquantia/AQ28*.cld
    elif [ "${MACHINE}" = "lx2160aqds" ];then
        rm -rf ${DEPLOYDIR}/lx2-phy/aquantia/AQR*.cld
    fi
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_MACHINE = "(lx2160a)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
