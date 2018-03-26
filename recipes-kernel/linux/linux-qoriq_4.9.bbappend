SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/linux;nobranch=1"
SRCREV = "cca40786ef656bc4dd1c91494206421be80dc19b"

do_deploy_append_ls1012a () {
    rm -fr ${DEPLOYDIR}/itbImage*
}
