DESCRIPTION = "Merge prebuilt/extra files into rootfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit update-rc.d

SIG = "${@bb.utils.contains('DISTRO_FEATURES', 'singleboot', 'true', 'false', d)}"
MFT = "${@bb.utils.contains('DISTRO_FEATURES', 'mft', 'true', 'false', d)}"
DEPENDS_append = " update-rc.d-native"
SRC_URI = "file://merge"
S = "${WORKDIR}"

INITSCRIPT_NAME = "eds-init"

MERGED_DST ?= "${bindir}"
do_install () {
    install -d ${D}/${MERGED_DST}
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rcS.d
    if [ "${MFT}" = "true" ];then
        sed -i "13,16c  \ \t /usr/bin/af-agent -host \$(ip route show | grep default | awk '{print \$3}')\n \t exit 0" ${WORKDIR}/merge/eds-init
    fi
    install -m 0755    ${WORKDIR}/merge/eds-init       ${D}${sysconfdir}/init.d
    #ln -s   ${D}${sysconfdir}/init.d/openil-init ${D}${sysconfdir}/rcS.d/S60openil-init
    update-rc.d -r ${D} eds-init  start 30 5 .
    find ${WORKDIR}/merge/ -maxdepth 1 -mindepth 1 -not -name README \
    -exec install -m 0755  '{}' ${D}/${MERGED_DST}/ \;
    find ${WORKDIR}/merge/ -maxdepth 1 -mindepth 1 -exec rm -fr '{}' \;
    if [ "${SIG}" = "true" ];then
	mv ${D}/${MERGED_DST}/ota-update-singleboot ${D}/${MERGED_DST}/ota-update
    fi
}

do_unpack[nostamp] = "1"
do_install[nostamp] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} = "/*"
ALLOW_EMPTY_${PN} = "1"
INSANE_SKIP_${PN} = "debug-files dev-so already-stripped file-rdeps"
