SRCREV = "b0a07cfcc7d63c3d2e65843009a89bef1a10f4cf"

PARALLEL_MAKE = ""

PPA_PATH_lx2160a = "lx2160"

do_compile () {
    export CROSS_COMPILE="${WRAP_TARGET_PREFIX}"
    cd ${S}/ppa
    if [ ${MACHINE} = ls1012afrdm ];then
        ./build  frdm-fit ${PPA_PATH}
    else
        ./build  rdb-fit ${PPA_PATH}
    fi
    cd ${S}
}

do_compile_lx2160a () {
    export CROSS_COMPILE="${WRAP_TARGET_PREFIX}"
    cd ${S}/ppa
    ./build  emu-fit ${PPA_PATH}
    cd ${S}
}

COMPATIBLE_MACHINE = "(lx2160a)"
