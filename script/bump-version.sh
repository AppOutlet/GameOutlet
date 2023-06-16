#!/bin/bash
PATH=$PATH:$PWD/script
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
cat snap/snapcraft.yaml|sed -r "s/version: $CURRENT_VERSION$/version: $NEW_VERSION/g" > snap/snapcraft.yaml.tmp
mv snap/snapcraft.yaml.tmp snap/snapcraft.yaml

echo "Updating build.gradle version"
cat build.gradle.kts|sed -r "s/version = \"$CURRENT_VERSION\"$/version = \"$NEW_VERSION\"/g" > build.gradle.kts.tmp
mv build.gradle.kts.tmp build.gradle.kts

echo "Updating build.gradle version"
cat build.gradle.kts|sed -r "s/packageVersion = \"$CURRENT_VERSION\"$/packageVersion = \"$NEW_VERSION\"/g" > build.gradle.kts.tmp
mv build.gradle.kts.tmp build.gradle.kts

echo "Updating AboutScreen version"
cat src/commonMain/kotlin/appoutlet/gameoutlet/feature/about/composable/AboutScreen.kt|sed -r "s/private const val VERSION = \"[0-9.]+\"$/private const val VERSION = \"$NEW_VERSION\"/g" > src/commonMain/kotlin/appoutlet/gameoutlet/feature/about/composable/AboutScreen.kt.tmp
mv src/commonMain/kotlin/appoutlet/gameoutlet/feature/about/composable/AboutScreen.kt.tmp src/commonMain/kotlin/appoutlet/gameoutlet/feature/about/composable/AboutScreen.kt

echo "$NEW_VERSION" > .version

git add .
git commit --no-verify -am "chore: bump to version $NEW_VERSION"
git push
git tag v$NEW_VERSION
git push origin v$NEW_VERSION