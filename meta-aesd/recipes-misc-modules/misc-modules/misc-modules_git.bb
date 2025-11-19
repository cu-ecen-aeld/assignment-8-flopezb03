# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-flopezb03.git;protocol=ssh;branch=main"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "c6e254f75bd056d7e9b6fd8cad6fbaff2278cfca"

S = "${WORKDIR}/git/${BPN}"
# BPN == misc-modules

inherit module

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

inherit update-rc.d
INITSCRIPT_NAME:${PN} = "misc-modules-init"
INITSCRIPT_PACKAGES = "${PN}"
SRC_URI += "file://misc-modules-init"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/misc-modules-init ${D}${sysconfdir}/init.d/
}
FILES:${PN} += "${sysconfdir}/init.d/misc-modules-init"

