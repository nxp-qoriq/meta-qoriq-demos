# Copyright (C) 2015 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Freescale Package group for core tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS_${PN} = " \
    e2fsprogs \
    e2fsprogs-badblocks \
    e2fsprogs-e2fsck \
    e2fsprogs-tune2fs  \
    i2c-tools \
    kmod \
    kernel-modules \
    libhugetlbfs \
    lmsensors-sensors \
    memtester \
    pkgconfig \
    python-subprocess \
    python-datetime \
    python-json \
    procps \
    minicom \
    coreutils \
    elfutils \
    file \
    psmisc \
    sysfsutils \
    sysklogd \
    sysstat \
"

IPC_PKGS = " \
    ipc-module-multi \
    ipc-module-single \
    ipc-ust \
"

DPAA_PKGS = " \
    eth-config \
    fmc \
    hypervisor-partman \
"
DPAA_PKGS_ls102xa = ""
DPAA_PKGS_fsl-lsch3 = ""
DPAA_PKGS_ls1043ardb-be ="eth-config fmc"
DPAA_PKGS_ls1043aqds-be ="eth-config fmc" 
DPAA_PKGS_remove_fsl-lsch2 = "hypervisor-partman"

PMETOOLS ?= "pme-tools"

RDEPENDS_${PN}_append_qoriq = "\
    merge-files \
    ${DPAA_PKGS} \
"

RDEPENDS_${PN}_append_e500v2 = " \
    cantest \
    fsl-pme \
    libppc \
    testfloat \
"

RDEPENDS_${PN}_append_qoriq = " gptfdisk"
RDEPENDS_${PN}_append_ls1012a = " hostapd"

DPAA_PKGS_ls1043a-32b ="eth-config fmc"
DPAA_PKGS_ls1046a-32b ="eth-config fmc"
DPAA_PKGS_ls1012a = "ppfe-firmware"

RDEPENDS_${PN}_append_qoriq = " pciutils cryptodev-linux cryptodev-module cryptodev-tests ifenslave"
RDEPENDS_${PN}_append_ls1043ardb = " \
    restool \
    fio \
"
RDEPENDS_${PN}_append_ls1046ardb = " \
    fio \
    restool \
"
RDEPENDS_${PN}_append_ls1088a = " \
    devmem2 \
    gpp-aioptool \
    ofp \
    fio \
    restool \
"
RDEPENDS_${PN}_append_ls2088a = " \
    devmem2 \
    gpp-aioptool \
    ofp \
    fio \
    restool \
"
RDEPENDS_${PN}_append_lx2160a = " \
    devmem2 \
    dpdk \
    gdbserver \
    glibc-utils \
    openssh-sftp-server \
    restool \
"
