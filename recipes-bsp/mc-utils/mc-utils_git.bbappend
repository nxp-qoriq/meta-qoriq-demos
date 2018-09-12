SRCREV = "e085f367f5e16d0f63a1b24861843af06bee48ed"

MC_CFG_lx2160aqds = "lx2160a/QDS"
MC_CFG_lx2160ardb = "lx2160a/RDB"

do_install_lx2160a () {
	oe_runmake -C config

	install -d ${D}/boot/mc-utils
	cp -r ${S}/config/${MC_CFG}/*.dtb ${D}/boot/mc-utils
}

do_deploy_lx2160a () {
	install -d ${DEPLOYDIR}/mc-utils
	cp -r ${S}/config/${MC_CFG}/*.dtb ${DEPLOYDIR}/mc-utils
}


COMPATIBLE_MACHINE = "(ls1088a|ls2088a|lx2160a)"
