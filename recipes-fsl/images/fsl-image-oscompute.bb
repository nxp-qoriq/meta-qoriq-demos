# Copyright (C) 2012-2015 Freescale Semiconductor Inc.

require recipes-fsl/images/fsl-image-core.bb

IMAGE_INSTALL += " \
    kernel-image \
    gdb \
    perf \
    packagegroup-core-buildessential \
    packagegroup-fsl-virtualization \
    libvirt libxslt libxml2 \
    libvirt-python \
    libvirt-python-dbg \
    ntp \
    python-pip git python-setuptools python-numpy python-lxml python-cryptography vlan rpm \
    python-docutils python-pycrypto python-dev \
    python-cffi python-cryptography python-greenlet python-psutil python-six \
    nova-compute \
    python-netifaces openssl openssl-qoriq openssl-dev\
"

IMAGE_FSTYPES = "tar.gz ext2.gz ext2.gz.u-boot"

# copy rootfs image into rootfs
inherit fsl-utils
ROOTFS_POSTPROCESS_COMMAND += "rootfs_copy_uimage;"

do_rootfs[depends] += "u-boot-mkimage-native:do_populate_sysroot"

