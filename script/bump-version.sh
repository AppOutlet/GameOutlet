#!/bin/bash
PATH=$PATH:$PWD/ci-tasks
CURRENT_VERSION=`cat .version`

if [ -z "$CURRENT_VERSION" ]; then
  echo "No old version found!"
  exit 1;
fi

if [ -z "$1" ]; then
  NEW_VERSION=`semver bump minor $CURRENT_VERSION`
else
  NEW_VERSION=$1
fi

if ! grep -q "$CURRENT_VERSION" build.gradle.kts; then
  echo "Failed to find existing version in build.gradle.kts!"
  exit 1;
fi

if ! grep -q "$CURRENT_VERSION" snap/snapcraft.yaml; then
  echo "Failed to find existing version in snapcraft.yaml"
  exit 1;
fi

echo "Updating snapcraft version"
cat snap/snapcraft.yaml|sed -r "s/version: [0-9.]+$/version: $NEW_VERSION/g" > snap/snapcraft.yaml.tmp
mv snap/snapcraft.yaml.tmp snap/snapcraft.yaml

echo "Updating build.gradle version"
cat build.gradle.kts|sed -r "s/version = \"[0-9.]+\"$/version = \"$NEW_VERSION\"/g" > build.gradle.kts.tmp
mv build.gradle.kts.tmp build.gradle.kts

echo "Updating build.gradle version"
cat build.gradle.kts|sed -r "s/packageVersion = \"[0-9.]+\"$/packageVersion = \"$NEW_VERSION\"/g" > build.gradle.kts.tmp
mv build.gradle.kts.tmp build.gradle.kts

echo "$NEW_VERSION" > .version

#git commit --no-verify -am "Bump to version $NEW_VERSION"
#git push origin main
#git tag v$NEW_VERSION
#git push origin v$NEW_VERSION