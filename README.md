## jitpack 추가
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    implementation("com.github.sarang628:RestaurantOverView:27bd106ba5")
}
```

```
git submodule add (or git clone) https://github.com/sarang628/restaurant_overview_di.git
```

## [Hilt 추가](https://github.com/sarang628/HiltTest?tab=readme-ov-file#for-torang)

## usecase 모듈 추가

RestaurantFeedsViewModel 의 usecase를 주입해 줘야함.

```
git submodule add (or git clone) https://github.com/sarang628/restauarnt_info_di.git
git submodule add (or git clone) https://github.com/sarang628/restaurant_detail_di.git
implementation("com.github.sarang628:RestaurantInfo:acb554a878")
implementation("com.github.sarang628:ComposePermissionTest:5159bc3d34")
implementation("com.google.accompanist:accompanist-permissions:0.32.0")
implementation("com.google.android.gms:play-services-location:21.1.0")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
```

## [usecase에 필요한 저장소를 추가](https://github.com/sarang628/TorangRepository?tab=readme-ov-file#%EB%AA%A8%EB%93%88-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0)

## [이미지 모듈 추가](https://github.com/sarang628/CommonImageLoader?tab=readme-ov-file#%EC%9D%B4%EB%AF%B8%EC%A7%80-%EB%A1%9C%EB%93%9C-%EB%AA%A8%EB%93%88-%EC%B6%94%EA%B0%80)

```
CompositionLocalProvider(
    LocalRestaurantOverViewImageLoader provides restaurantOverViewImageLoader,
    LocalRestaurantOverviewRestaurantInfo provides restaurantOverViewRestaurantInfo,
) {
    RestaurantOverViewScreen(restaurantId = 234)
}

val restaurantOverViewImageLoader: RestaurantOverViewImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}

val restaurantOverViewRestaurantInfo: RestaurantOverviewRestaurantInfo = {
    CompositionLocalProvider(LocalRestaurantInfoImageLoader provides restaurantInfoImageLoader){
        RestaurantInfoWithPermission(restaurantId = it, viewModel = BestPracticeViewModel())
    }
}

val restaurantInfoImageLoader: RestaurantInfoImageLoader = { modifier, url, width, height, scale ->
    // 여기서 실제 이미지 로딩 구현 예시
    provideTorangAsyncImage().invoke(modifier, url, width, height, scale)
}
```