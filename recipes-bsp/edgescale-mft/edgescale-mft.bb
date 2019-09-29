LICENSE = "af-agent"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/build/af-agent;md5=302ea000bd8b55d0a5aeded9b9765836"
SRC_URI = "\
	git://${GO_IMPORT}.git;protocol=ssh;nobranch=1 \
        git://github.com/x-pkg/requests;nobranch=1;destsuffix=git/src/github.com/x-pkg/requests;name=requests \
	"
SRCREV = "9aa91e3f2d34c3105b887e5209e8abfff3296fab"
SRCREV_requests = "7e8a46142ada42d838e4a6f4810689a5a1f1c538"

DEPENDS = "\
           openssl \
          "

GO_IMPORT = "bitbucket.sw.nxp.com/~nxa22939/edgescale-mft-agent"

S = "${WORKDIR}/git"
inherit go
inherit goarch

# This disables seccomp and apparmor, which are on by default in the
# go package. 
WRAP_TARGET_PREFIX ?= "${TARGET_PREFIX}"
export CROSS_COMPILE="${WRAP_TARGET_PREFIX}"
export OPENSSL_PATH="${RECIPE_SYSROOT}/usr"

do_compile() {
        export GOARCH="${TARGET_GOARCH}"
        export CGO_ENABLED="1"
        export CFLAGS=""
        export LDFLAGS=""
        export CGO_CFLAGS="${BUILDSDK_CFLAGS} --sysroot=${STAGING_DIR_TARGET}"
        export CGO_LDFLAGS="${BUILDSDK_LDFLAGS} --sysroot=${STAGING_DIR_TARGET}"
        
        rm -rf ${S}/import/vendor/cmd/af-agent
        mkdir -p ${S}/import/vendor/cmd
        cp -rf ${S}/src/${GO_IMPORT}/cmd/af-agent ${S}/import/vendor/cmd/
        cp -rf ${S}/src/${GO_IMPORT}/pkg ${S}/import/vendor/
        cd ${S}/import/vendor/cmd/af-agent
	${GO} build --ldflags="-w -s" af-agent.go
}

do_install() {
	install -d ${D}/${bindir}
        install -d ${D}/${sysconfdir}
        install -d ${D}/${includedir}/af-agent

        cp -r ${S}/import/vendor/cmd/af-agent/af-agent ${D}/${bindir}
        cp -r ${S}/import/vendor/pkg ${D}/${includedir}/aft-agent/
}


FILES_${PN} += "${includedir}/* /usr/local/*"
INSANE_SKIP_${PN} += "already-stripped dev-deps"
deltask compile_ptest_base
