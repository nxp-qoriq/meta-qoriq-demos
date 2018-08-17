do_install_append() {
    mv ${D}${bindir}/ftpd ${D}${sbindir}/in.ftpd
}

ALTERNATIVE_LINK_NAME[ftpd] = "${sbindir}/ftpd"
ALTERNATIVE_TARGET[ftpd] = "${sbindir}/in.ftpd"
FILES_${PN}-ftpd += "${sbindir}/in.ftpd"
FILES_${PN}-ftpd-dbg += "${bindir}/.debug/in.ftpd"
