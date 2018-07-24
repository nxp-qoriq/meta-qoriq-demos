# Copyright (C) 2015 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Freescale Package group for vitualization"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PACKAGE_ARCH = "${MACHINE_ARCH}"
inherit packagegroup

PACKAGES = "${PN}"

RDEPENDS_${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', \
    'libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    lxc \
    qemu', \
    '', d)} \
" 

RDEPENDS_${PN}_remove_qoriq-ppc = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', \
    'libvirt \
    libvirt-libvirtd \
    libvirt-virsh \
    lxc', \
    '', d)} \
"

DOCKER_PKGS = " \
    docker \
    docker-registry \
"

RDEPENDS_${PN}_append_ls1043ardb = " ovs-dpdk ${DOCKER_PKGS}"
RDEPENDS_${PN}_append_ls1046ardb = " ovs-dpdk ${DOCKER_PKGS}"
RDEPENDS_${PN}_append_ls1088ardb = " ovs-dpdk ${DOCKER_PKGS}"
RDEPENDS_${PN}_append_ls2088ardb = " ovs-dpdk ${DOCKER_PKGS}"
RDEPENDS_${PN}_append_lx2160a = " ovs-dpdk ${DOCKER_PKGS}"
