require recipes-core/images/core-image-minimal.bb

IMAGE_FSTYPES = "tar.gz ext2.gz ext2.gz.u-boot"

LICENSE = "MIT"

EXTRA_IMAGEDEPENDS_remove_ls1012ardb = " rcw"
EXTRA_IMAGEDEPENDS_append_ls1012ardb = " rcw-bin"

IMAGE_INSTALL_append = " \
    dpdk \
    kernel-modules \
    merge-file \
    ppfe-firmware \
"
