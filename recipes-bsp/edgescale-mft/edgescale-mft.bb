LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SRC_URI = "file://af-agent \
	"

S = "${WORKDIR}"


do_install() {
        install -d ${D}/${bindir}
        install -d ${D}/${sysconfdir}

	cp -rf ${S}/af-agent ${D}/${bindir}
}


INSANE_SKIP_${PN} += "already-stripped dev-deps"
