RCREV = "472c9c380669eb7a26819a52598632f257b3e72b"

do_install_append () {
    cp --dereference -R crypto  ${D}${includedir}
}
