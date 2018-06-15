DESCRIPTION = "The Management Complex (MC) is a key component of DPAA"
SECTION = "mc-utils"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=386a6287daa6504b7e7e5014ddfb3987 \
"

DEPENDS += "dtc-native"

inherit deploy

SRC_URI = "git://gitolite3@git.codeaurora.org:22/external/private_lx2160a/mc-utils;nobranch=1;protocol=ssh"
SRCREV = "777c0024f207ca3e90b75b3e451841c29eadf55d"

S = "${WORKDIR}/git"

MC_CFG ?= ""
MC_CFG_ls1088a = "ls1088a/RDB"
MC_CFG_ls2088a = "ls2088a/RDB"
MC_CFG_lx2160a = "lx2160a"

do_install () {
	oe_runmake -C config 

	install -d ${D}/boot/mc-utils
	cp -r ${S}/config/${MC_CFG}/*.dtb ${D}/boot/mc-utils
	if [ -e ${S}/config/${MC_CFG}/custom ]; then
	    install -d ${D}/boot/mc-utils/custom
	    cp -r ${S}/config/${MC_CFG}/custom/*.dtb ${D}/boot/mc-utils/custom
	fi
}

do_deploy () {
	install -d ${DEPLOYDIR}/mc-utils
	cp -r ${S}/config/${MC_CFG}/*.dtb ${DEPLOYDIR}/mc-utils
	if [ -e ${S}/config/${MC_CFG}/custom ]; then
	    install -d ${DEPLOYDIR}/mc-utils/custom
	    cp -r ${S}/config/${MC_CFG}/custom/*.dtb ${DEPLOYDIR}/mc-utils/custom
	fi
        
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"
COMPATIBLE_MACHINE = "(ls1088a|ls2088a|lx2160a)"
