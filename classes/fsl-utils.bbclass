rootfs_copy_core_image() {
    mkdir -p ${IMAGE_ROOTFS}/boot
    cp ${DEPLOY_DIR_IMAGE}/fsl-image-core-${MACHINE}.ext2.gz ${IMAGE_ROOTFS}/boot/
}

rootfs_copy_uimage() {
     mkdir -p ${IMAGE_ROOTFS}/boot
     cp ${DEPLOY_DIR_IMAGE}/Image Image-new.bin
     gzip Image-new.bin
     mkimage -A arm64 -O linux -T kernel -C gzip -a 0x80080000 -e 0x80080000 -n linux-3.19 -d Image-new.bin.gz uImage
     cp uImage ${IMAGE_ROOTFS}/boot/
     cp ${DEPLOY_DIR_IMAGE}/Image-fsl-lx2160a-rdb.dtb ${IMAGE_ROOTFS}/boot/
     rm Image-new.bin.gz uImage
}

