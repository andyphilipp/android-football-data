Android Football Data
---
Android client for accessing football data. Teams, players, fixtures, live results and more.
The data is provided by [Daniel Freitag's RESTFul football API](http://api.football-data.org).

# Sample Usage
```java
FootballData footballData = FootballData.getInstance();

footballData.fixturesForTeam(mId).enqueue(new Callback<ArrayList<Fixture>>() {
  @Override
    public void onResponse(Call<ArrayList<Fixture>> call, Response<ArrayList<Fixture>> response) {
        Fixture firstFixture = response.body().get(0);
        String awayTeam = firstFixture.getAwayTeamName();
        String homeTeam = firstFixture.getHomeTeamName();
        Result result = firstFixture.getResult();
        String display = String.format(Locale.getDefault(), "%s %d - %d %s", homeTeam, result.getGoalsHomeTeam(), result.getGoalsAwayTeam(), awayTeam);
        Toast.makeText(getActivity(), display, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<ArrayList<Fixture>> call, Throwable t) {
        Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
});
```

# Set API key
```java
FootballData footballData = FootballData.setApiKey("<YOUR API KEY HERE>");();
```
Get your API key [here](http://api.football-data.org/register).
