FILESEXTRAPATHS_prepend := "${THISDIR}/gcc:"
#SRC_URI_append_e500v2 += "file://0001-remove-Obsolete-configurations.patch \
#"
SRC_URI_e500v2 = "\
           ${BASEURI} \
           file://0001-gcc-4.3.1-ARCH_FLAGS_FOR_TARGET.patch \
           file://0003-gcc-poison-system-directories.patch \
           file://0005-gcc-4.3.3-SYSROOT_CFLAGS_FOR_TARGET.patch \
           file://0006-64-bit-multilib-hack.patch \
           file://0007-optional-libstdc.patch \
           file://0008-gcc-disable-MASK_RELAX_PIC_CALLS-bit.patch \
           file://0009-COLLECT_GCC_OPTIONS.patch \
           file://0010-Use-the-defaults.h-in-B-instead-of-S-and-t-oe-in-B.patch \
           file://0011-fortran-cross-compile-hack.patch \
           file://0012-cpp-honor-sysroot.patch \
           file://0013-MIPS64-Default-to-N64-ABI.patch \
           file://0014-Define-GLIBC_DYNAMIC_LINKER-and-UCLIBC_DYNAMIC_LINKE.patch \
           file://0015-gcc-Fix-argument-list-too-long-error.patch \
           file://0016-Disable-sdt.patch \
           file://0017-libtool.patch \
           file://0018-gcc-armv4-pass-fix-v4bx-to-linker-to-support-EABI.patch \
           file://0019-Use-the-multilib-config-files-from-B-instead-of-usin.patch \
           file://0020-Avoid-using-libdir-from-.la-which-usually-points-to-.patch \
           file://0021-export-CPP.patch \
           file://0022-Disable-the-MULTILIB_OSDIRNAMES-and-other-multilib-o.patch \
           file://0023-Ensure-target-gcc-headers-can-be-included.patch \
           file://0024-gcc-4.8-won-t-build-with-disable-dependency-tracking.patch \
           file://0025-Don-t-search-host-directory-during-relink-if-inst_pr.patch \
           file://0026-Use-SYSTEMLIBS_DIR-replacement-instead-of-hardcoding.patch \
           file://0027-aarch64-Add-support-for-musl-ldso.patch \
           file://0028-libcc1-fix-libcc1-s-install-path-and-rpath.patch \
           file://0029-handle-sysroot-support-for-nativesdk-gcc.patch \
           file://0030-Search-target-sysroot-gcc-version-specific-dirs-with.patch \
           file://0031-Fix-various-_FOR_BUILD-and-related-variables.patch \
           file://0032-nios2-Define-MUSL_DYNAMIC_LINKER.patch \
           file://0033-Add-ssp_nonshared-to-link-commandline-for-musl-targe.patch \
           file://0034-libgcc-Add-knob-to-use-ldbl-128-on-ppc.patch \
           file://0035-Link-libgcc-using-LDFLAGS-not-just-SHLIB_LDFLAGS.patch \
           file://0036-libgcc_s-Use-alias-for-__cpu_indicator_init-instead-.patch \
           file://0037-sync-gcc-stddef.h-with-musl.patch \
           file://0038-fix-segmentation-fault-in-precompiled-header-generat.patch \
           file://0039-Fix-for-testsuite-failure.patch \
           file://0040-Re-introduce-spe-commandline-options.patch \
           file://0041-ARC-fix-spec-gen.patch \
           file://0001-remove-Obsolete-configurations.patch \
           ${BACKPORTS} \
"
