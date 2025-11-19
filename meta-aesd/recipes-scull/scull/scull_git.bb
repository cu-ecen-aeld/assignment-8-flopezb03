# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-flopezb03.git;protocol=ssh;branch=main \
           file://0001-Makefile-modified.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "c6e254f75bd056d7e9b6fd8cad6fbaff2278cfca"

S = "${WORKDIR}/git"

inherit module


inherit update-rc.d
INITSCRIPT_NAME:${PN} = "scull-init"
INITSCRIPT_PACKAGES = "${PN}"
SRC_URI += "file://scull-init"


do_compile() {
    #oe_runmake -C ${S} KERNELDIR=${STAGING_KERNEL_DIR} 
    oe_runmake -C ${S} KERNELDIR=${STAGING_KERNEL_DIR} M=${S}
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -m 0644 ${S}/scull/*.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/scull-init ${D}${sysconfdir}/init.d/
}
FILES:${PN} += "${sysconfdir}/init.d/scull-init"
