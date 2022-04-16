#!/usr/bin/python3
import argparse
import subprocess
from distutils.spawn import find_executable
from urllib.parse import quote


def look_for_or_abort(name: str):
    path = find_executable(name)
    if path is None:
        print(f"You need to have {name} installed on your system and on the path for this script to work")
        exit(1)


def parse_cli_arguments():
    parser = argparse.ArgumentParser(description='Creates an OTP token')
    parser.add_argument('-i', '--issuer', type=str, help='Issuer', default='ACME Co')
    parser.add_argument('-a', '--account', type=str, help='Account', default='daniele@example.com')
    parser.add_argument('-s', '--secret', type=str, help='Secret', default='NEMVJLHFODKLTKQ3K2G6F36YSBWNP5EK')
    parser.add_argument('-d', '--digits', type=int, help='Digits', default=6)
    parser.add_argument('-p', '--period', type=int, help='Period', default=30)

    return parser.parse_args()


def print_code():
    qrencode = 'qrencode'
    look_for_or_abort(qrencode)
    args = parse_cli_arguments()
    issuer = quote(args.issuer)
    account = quote(args.account)
    secret = quote(args.secret)
    digits = quote(str(args.digits))
    period = quote(str(args.period))
    otpurl = f"otpauth://totp/{issuer}:{account}?secret={secret}&issuer={issuer}&algorithm=SHA1&digits={digits}&period={period}"
    ffmpeg_args = [qrencode, '-t', 'ansiutf8', otpurl]
    subprocess.call(ffmpeg_args)


if __name__ == '__main__':
    print_code()
