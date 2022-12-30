$name = $args[0]
$part = $args[1]
# if name is not provided, return error
if (!$name) {
    Write-Error "Name is required"
    exit 1
}

Set-Location $name
if ([int]$name -ge 1 -and [int]$name -le 5) {
    rustc "$name$part.rs"
    if (Test-Path "$name$part.exe") {
        Invoke-Expression "./$name$part.exe"
        Remove-Item "$name$part.exe" && Remove-Item "$name$part.pdb"
    }
}
if ([int]$name -ge 6 -and [int]$name -le 10) {
    kotlinc "$name$part.kt" -include-runtime -d "$name$part.jar" 
    if (Test-Path "$name$part.jar") {
        Invoke-Expression "java -jar $name$part.jar"
        Remove-Item "$name$part.jar"
    }
}

Set-Location ..