SECTION = "dp_firmware"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://dp/COPYING;md5=5ab1a30d0cd181e3408077727ea5a2db \
"

DEPENDS += "dtc-native"

inherit deploy

SRC_URI = "git://bitbucket.sw.nxp.com/dash/firmware-cadence.git;protocol=ssh;nobranch=1"
SRCREV = "98d51b8a9edf74b09b2977f206107a742c32b116"

S = "${WORKDIR}/git"


do_install () {

        install -d ${D}/boot/dp-firmware
        cp -r ${S}/dp/*.bin ${D}/boot/dp-firmware
}

do_deploy () {
        install -d ${DEPLOYDIR}/dp-firmware
        cp -r ${S}/dp/*.bin ${DEPLOYDIR}/dp-firmware
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"
COMPATIBLE_MACHINE = "(ls1028ardb)"

