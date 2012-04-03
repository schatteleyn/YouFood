#!/bin/bash
echo "----- Compiling less stylesheets -----"
echo "> Default"
lessc -x stylesheets/bootstrap/less/bootstrap.less stylesheets/bootstrap/bootstrap.min.css
echo "> Responsive"
lessc -x stylesheets/bootstrap/less/responsive.less stylesheets/bootstrap/bootstrap-responsive.min.css
echo "----- Grouping javascript files -----"
echo "" > javascripts/bootstrap/bootstrap.js
for file in javascripts/bootstrap/files/*.js
do
    echo "> $file"
    cat $file >> javascripts/bootstrap/bootstrap.js
done
echo "----- Done -----"
