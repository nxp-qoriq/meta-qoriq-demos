SUMMARY = "A FIT image comprising the Linux image, dtb and rootfs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

KERNEL_IMAGE ?= "${KERNEL_IMAGETYPE}"
DTS_FILE ?= "${KERNEL_DEVICETREE}"
ROOTFS_IMAGE ?= "fsl-image-core"
KERNEL_ITS ?= "kernel.its"

SRC_URI = "file://${KERNEL_ITS}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
do_populate_sysroot[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_rpm[noexec] = "1"

do_fetch[nostamp] = "1"
do_unpack[nostamp] = "1"
do_deploy[nostamp] = "1"
do_deploy[depends] += "u-boot-mkimage-native:do_populate_sysroot virtual/kernel:do_build ${ROOTFS_IMAGE}:do_build"
ITB_SUFFIX ?= "${DATETIME}"
ITB_SUFFIX[vardepsexclude] = "DATETIME"

do_deploy () {
    install -d ${DEPLOYDIR}
    cp ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGE} .
    rm -f ${KERNEL_IMAGE}.gz
    gzip ${KERNEL_IMAGE}

    DTB_FILE="${KERNEL_IMAGETYPE}-`basename ${DTS_FILE}`";
    ITB_BASENAME="kernel-${MACHINE}-${ITB_SUFFIX}"
    ITB_SYMLINK="kernel-${MACHINE}"

    cp ${WORKDIR}/${KERNEL_ITS} kernel.its
    sed -i -e "s,kernel-image.gz,${KERNEL_IMAGE}.gz," kernel.its
    sed -i -e "s,freescale.dtb,${DEPLOY_DIR_IMAGE}/${DTB_FILE}," kernel.its
    sed -i -e "s,rootfs.ext2.gz,${DEPLOY_DIR_IMAGE}/${ROOTFS_IMAGE}-${MACHINE}.ext2.gz," kernel.its

    mkimage -f kernel.its ${ITB_BASENAME}.itb

    install -m 644 ${ITB_BASENAME}.itb ${DEPLOYDIR}/
    ln -sf ${ITB_BASENAME}.itb ${DEPLOYDIR}/${ITB_SYMLINK}.itb
}
addtask deploy before build

COMPATIBLE_MACHINE = "(fsl-lsch3)"
