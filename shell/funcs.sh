#!/bin/bash

infoMsg () {
  infoMessage="$1"
  echo "`date +%Y-%m-%d:%H:%M:%S` | INFO | ${infoMessage}"
}

warnMsg () {
  warningMessage="$1"
  echo "`date +%Y-%m-%d:%H:%M:%S` | WARN | ${warningMessage}"
}

errorMsg () {
  errorMessage="$1"
  echo "`date +%Y-%m-%d:%H:%M:%S` | ERROR | ${errorMessage}"
  exit 1
}