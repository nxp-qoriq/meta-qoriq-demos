DESCRIPTION = "Edgescale customized domain and certificates"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch

SRC_URI = "file://config.yml"
S = "${WORKDIR}"

python () {
    esdomainsuffix = d.getVar("ES_DOMAIN_SUFFIX")
    if not esdomainsuffix:
        raise bb.parse.SkipRecipe("Please define ES_DOMAIN_SUFFIX in conf/local.conf")
}

do_install () {
    install -d ${D}/usr/local/edgescale/conf
    cp -fr ${S}/config.yml ${D}/usr/local/edgescale/conf
    sed -i "s/edgescale.org/${ES_DOMAIN_SUFFIX}/g" ${D}/usr/local/edgescale/conf/config.yml

    if [ -n "${ES_CERTIFICATE_PATH}" ]; then
      install -d ${D}/usr/local/share/ca-certificates
      cp -fr ${ES_CERTIFICATE_PATH}/* ${D}/usr/local/share/ca-certificates
    fi
}
do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} = "/*"
ALLOW_EMPTY_${PN} = "1"
INSANE_SKIP_${PN} = "debug-files dev-so"
