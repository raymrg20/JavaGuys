import java.util.Scanner;

public class IrrigationSystems {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Soil Moisture
        System.out.print("Enter soil moisture content (%): ");
        double soilMoisture = scanner.nextDouble();

        // Rainfall Forecast
        System.out.print("Enter expected rainfall in the next 24 hours (mm): ");
        double rainfall = scanner.nextDouble();

        // Temperature
        System.out.print("Enter current temperature (°C): ");
        double temperature = scanner.nextDouble();

        // Humidity
        System.out.print("Enter current humidity (%): ");
        double humidity = scanner.nextDouble();

        // Crop Type
        System.out.print("Enter crop type (e.g., tomatoes): ");
        String cropType = scanner.next();

        // Growth Stage
        System.out.print("Enter growth stage (e.g., seedling, vegetative, flowering, fruiting): ");
        String growthStage = scanner.next();

        // Soil Type
        System.out.print("Enter soil type (sandy, loamy, clay): ");
        String soilType = scanner.next();

        // Water Availability
        System.out.print("Enter available water in storage (liters): ");
        double waterAvailability = scanner.nextDouble();

        // Determine if watering is needed
        boolean needsWater = shouldWaterCrops(soilMoisture, rainfall, temperature, humidity, cropType, growthStage, soilType, waterAvailability);

        // Output the result
        if (needsWater) {
            System.out.println("The crops need to be watered.");
        } else {
            System.out.println("The crops do not need to be watered.");
        }

        scanner.close();
    }

    public static boolean shouldWaterCrops(double soilMoisture, double rainfall, double temperature, double humidity, String cropType, String growthStage, String soilType, double waterAvailability){
        // Thresholds
        double minSoilMoisture = 20.0;
        double maxSoilMoisture = 60.0;
        double rainfallThreshold = 10.0;

        // Crop-specific water needs (tomatoes)
        double cropMinSoilMoisture = 50.0;
        double cropMaxSoilMoisture = 75.0;

        // Growth stage Multiplier for specific needs
        double growthStageMultiplier = getGrowthStageMultiplier(growthStage);
        double soilTypeMultiplier = getSoilTypeMultiplier(soilType);

        // Check soil Moisture Level
        if(soilMoisture < minSoilMoisture || soilMoisture > maxSoilMoisture){
            return true;
        } 

        // Check Rainfall Forecast
        if(rainfall > rainfallThreshold){
            return false;
        }

        // Crop Type and Growth Stage
        double adjustedMinSoilMoisture = cropMinSoilMoisture * growthStageMultiplier;
        double adjustedMaxSoildMoisture = cropMaxSoilMoisture * growthStageMultiplier;

        adjustedMinSoilMoisture *= soilTypeMultiplier;
        adjustedMaxSoildMoisture*= soilTypeMultiplier;

        // Water Availability
        if(waterAvailability <= 0){
            return false;
        }

        // Final Results based on all parameters
        if(soilMoisture < adjustedMinSoilMoisture || soilMoisture > adjustedMaxSoildMoisture){
            return true;
        }

        return false;
    }

    public static double getGrowthStageMultiplier(String growthStage){
        switch (growthStage.toLowerCase()) {
            case "seedling":
                return 0.8;
            case "vegetative":
                return 1.0;
            case "flowering":
                return 1.2;
            case "fruiting":
                return 1.4;
            default:
                return 1.0;
        }

    }

    public static double getSoilTypeMultiplier(String soilType){
        switch (soilType.toLowerCase()) {
            case "sandy":
                return 1.2;
            case "loamy":
                return 1.0;
            case "clay":
                return 0.8;
            default:
                return 1.0;
        }
    }
}