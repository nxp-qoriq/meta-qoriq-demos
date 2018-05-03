require fsl-image-mfgtool.bb

SUMMARY = "Small image to be used for evaluating the NXP socs"
DESCRIPTION = "Small image which includes some helpful tools and \
NXP-specific packages. It is much more embedded-oriented \
than fsl-image-full to evaluate the NXP socs."

LICENSE = "MIT"

IMAGE_INSTALL_append = " \
    packagegroup-fsl-tools-core \
    packagegroup-fsl-networking-core \
"
