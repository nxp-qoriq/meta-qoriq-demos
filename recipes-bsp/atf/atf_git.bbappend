SRC_URI = "git://bitbucket.sw.nxp.com/gitam/atf.git;protocol=ssh;nobranch=1"
SRCREV = "17f94e4315e81e3d1b22d863d9614d724e8273dc"

DEPENDS_append_qoriq-arm64 += "${@bb.utils.contains('DISTRO_FEATURES', 'optee', 'optee-os-qoriq', '', d)}"
DEPENDS_append_lx2160a += "ddr-phy"
chassistype_ls1046afrwy = "ls104x_1012"
