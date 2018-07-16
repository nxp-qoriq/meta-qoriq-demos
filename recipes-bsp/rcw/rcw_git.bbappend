LIC_FILES_CHKSUM = "file://LICENSE;md5=45a017ee5f4cfe64b1cddf2eb06cffc7"

SRCREV = "6ed93d52f1aa3b2ee20da8f674bcadda890ef8fe"

do_install () {
    if [ ${M} = ls2088ardb ]; then
        oe_runmake BOARDS=${M} DESTDIR=${D}/boot/rcw/ install
        oe_runmake BOARDS=${M}_rev1.1  DESTDIR=${D}/boot/rcw/ install
    else
        oe_runmake BOARDS=${M} DESTDIR=${D}/boot/rcw/ install
    fi
    for f in `find ${D}/boot/rcw/ -name "*qspiboot*"`;do
        if echo $f |grep -q "qspiboot_sben"; then
            continue
        fi
        f_swap=`echo $f |sed -e 's/qspiboot/qspiboot_swap/'`
        tclsh ${STAGING_BINDIR_NATIVE}/byte_swap.tcl $f $f_swap 8
        mv -f $f_swap $f
    done
}
